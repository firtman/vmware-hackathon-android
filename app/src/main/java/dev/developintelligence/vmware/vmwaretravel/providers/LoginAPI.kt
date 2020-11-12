package dev.developintelligence.vmware.vmwaretravel.providers

class LoginAPI {

    fun login(user: String, password: String, callback: (Boolean) -> Unit) {
        // This is a Dummy API to login a user
        if (user=="vmware" && password=="123456") {
            callback(true)
        } else {
            callback(false)
        }
    }
    
}