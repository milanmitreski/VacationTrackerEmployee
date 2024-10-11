package rbt.vacationemployee.utils

import java.util.Base64

object AuthHeaderUtil {
    fun getEmailFromAuthHeader(authHeader: String): String = String(Base64.getDecoder().decode(authHeader.split(" ")[1])).split(":")[0]
}
