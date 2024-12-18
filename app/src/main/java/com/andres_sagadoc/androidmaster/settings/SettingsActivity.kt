package com.andres_sagadoc.androidmaster.settings

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.andres_sagadoc.androidmaster.R
import com.andres_sagadoc.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// creando base de datos
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private var firsTime: Boolean = true

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_DARK_MODE = "key_dark_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        enableDarkModeActionBar()
        changeNameActionBar(getString(R.string.settings))

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.IO).launch {
            // Recuperando datos
            getSettings().filter { firsTime }.collect { settingsModel ->
                if (settingsModel != null) {
                    runOnUiThread {
                        binding.rsVolumen.setValues(settingsModel.volume.toFloat())
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.switchVibration.isChecked = settingsModel.vibration
                        binding.switchDarMode.isChecked = settingsModel.darkMode
                        firsTime = !firsTime
                    }
                }
            }
        }
        initUI()
        initSwitches()
    }

    private fun initSwitches() {
        // Definir ColorStateList para el thumb
        val thumbColorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),  // Estado activado (true)
                intArrayOf(-android.R.attr.state_checked)  // Estado desactivado (false)
            ),
            intArrayOf(
                ContextCompat.getColor(
                    this,
                    R.color.settings_switch_thumb_tint
                ),  // Cuando está activado
                Color.parseColor("#BDBDBD")   // Cuando está desactivado
            )
        )
        binding.switchBluetooth.thumbTintList = thumbColorStateList
        binding.switchVibration.thumbTintList = thumbColorStateList
        binding.switchDarMode.thumbTintList = thumbColorStateList

// Definir ColorStateList para el track
        val trackColorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),  // Estado activado (true)
                intArrayOf(-android.R.attr.state_checked)  // Estado desactivado (false)
            ),
            intArrayOf(
                ContextCompat.getColor(
                    this,
                    R.color.settings_switch_track_tint
                ),  // Verde cuando está activado
                Color.parseColor("#E0E0E0")   // Gris claro cuando está desactivado
            )
        )
        binding.switchBluetooth.trackTintList = trackColorStateList
        binding.switchVibration.trackTintList = trackColorStateList
        binding.switchDarMode.trackTintList = trackColorStateList
    }

    private fun initUI() {
        binding.rsVolumen.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }

        binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }
        binding.switchVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION, value)
            }
        }
        binding.switchDarMode.setOnCheckedChangeListener { _, value ->
            if (value) enableDarkMode()
            else disableDarkMode()
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)
            }
        }
    }

    // Guardando datos
    private suspend fun saveVolume(value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    // Guardando datos
    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preference -> preference[booleanPreferencesKey(key)] = value }
    }

    // Recuperando datos
    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map { preferences ->
            SettingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: false,
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true,

                )
        }
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
    }

    private fun enableDarkModeActionBar() {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                // Modo oscuro
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.background_app)))
            }
        }
    }

    private fun changeNameActionBar(name: String) {
        supportActionBar?.title = name
    }
}