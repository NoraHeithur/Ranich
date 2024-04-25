package com.example.ranich.di

import com.example.ranich.ui.screen.help_graph.HelpViewModel
import com.example.ranich.ui.screen.home_graph.HomeViewModel
import com.example.ranich.ui.screen.login.LogInViewModel
import com.example.ranich.ui.screen.setting_graph.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HelpViewModel)
    viewModelOf(::LogInViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SettingViewModel)
}