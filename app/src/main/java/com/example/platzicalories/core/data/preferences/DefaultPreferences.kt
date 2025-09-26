package com.example.platzicalories.core.data.preferences

import android.content.SharedPreferences
import com.example.platzicalories.core.domain.model.ActivityLevel
import com.example.platzicalories.core.domain.model.Gender
import com.example.platzicalories.core.domain.model.GoalType
import com.example.platzicalories.core.domain.model.UserInfo
import com.example.platzicalories.core.domain.preferences.Preferences
import androidx.core.content.edit


class DefaultPreferences(
    private val sharedPref: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit { putString(Preferences.KEY_GENDER, gender.name) }
    }

    override fun saveAge(age: Int) {
        sharedPref.edit { putInt(Preferences.KEY_AGE, age) }
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit { putFloat(Preferences.KEY_WEIGHT, weight) }
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit { putInt(Preferences.KEY_HEIGHT, height) }
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit { putString(Preferences.KEY_ACTIVITY_LEVEL, level.name) }
    }

    override fun saveGoalType(type: GoalType) {
        sharedPref.edit { putString(Preferences.KEY_GOAL_TYPE, type.name) }
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit { putFloat(Preferences.KEY_CARB_RATIO, ratio) }
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit { putFloat(Preferences.KEY_PROTEIN_RATIO, ratio) }
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit { putFloat(Preferences.KEY_FAT_RATIO, ratio) }
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(Preferences.KEY_AGE, -1)
        val height = sharedPref.getInt(Preferences.KEY_HEIGHT, -1)
        val weight = sharedPref.getFloat(Preferences.KEY_WEIGHT, -1f)
        val genderString = sharedPref.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharedPref.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPref.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = sharedPref.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPref.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPref.getFloat(Preferences.KEY_FAT_RATIO, -1f)

        return UserInfo(
            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPref.edit { putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow) }
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(
            Preferences.KEY_SHOULD_SHOW_ONBOARDING, true
        )
    }
}