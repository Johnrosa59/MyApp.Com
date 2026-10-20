package com.joaorosa.myappcom.presentation.auth

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

/**
 * Traduz as exceções do Firebase (que vêm em inglês) para mensagens em português.
 * Exceções do próprio app (IllegalArgumentException) já vêm em português e são mantidas.
 */
object FirebaseErrorTranslator {

    fun translate(e: Exception, defaultMessage: String): String {
        return when (e) {
            is FirebaseAuthInvalidCredentialsException -> when (e.errorCode) {
                "ERROR_INVALID_EMAIL" -> "O e-mail informado é inválido."
                "ERROR_WRONG_PASSWORD" -> "Senha incorreta."
                else -> "E-mail ou senha incorretos."
            }
            is FirebaseAuthInvalidUserException -> when (e.errorCode) {
                "ERROR_USER_DISABLED" -> "Esta conta foi desativada."
                else -> "Não existe uma conta com este e-mail."
            }
            is FirebaseAuthUserCollisionException -> "Este e-mail já está cadastrado."
            is FirebaseAuthWeakPasswordException -> "A senha é muito fraca. Use pelo menos 6 caracteres."
            is FirebaseTooManyRequestsException -> "Muitas tentativas. Aguarde um pouco e tente novamente."
            is FirebaseNetworkException -> "Sem conexão com a internet. Verifique sua rede."
            is IllegalArgumentException, is IllegalStateException ->
                e.localizedMessage ?: defaultMessage
            else -> defaultMessage
        }
    }
}
