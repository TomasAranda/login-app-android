package com.example.login_app

class EmptyUsernameOrPasswordException(message:String): Exception(message)

class UsernameAlreadyTakenException(message:String): Exception(message)

class WrongPasswordException(message:String): Exception(message)

class UsernameDoesNotExistException(message:String): Exception(message)