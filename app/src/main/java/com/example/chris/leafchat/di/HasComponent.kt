package com.example.chris.leafchat.di

/**
 * Created by Chris on 2/15/18.
 */
interface HasComponent<out T> {
    fun getComponent() : T
}