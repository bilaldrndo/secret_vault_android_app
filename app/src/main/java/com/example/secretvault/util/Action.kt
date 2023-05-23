package com.example.secretvault.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    NO_ACTION
}

fun String?.toAction(): Action {
    return when
    {
        this == "ADD" -> {
            return Action.ADD
        }
        this == "UPDATE" -> {
            return Action.UPDATE
        }
        this == "DELETE" -> {
            return Action.DELETE
        }
        this == "DELETE_ALL" -> {
            return Action.DELETE_ALL
        }
        else -> {
            return Action.NO_ACTION
        }
    }
}