abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = arrayListOf<ErrorCode>()
        //Проверяем поле на null
        if (!value.isNullOrEmpty()) {
            //Проверяем поле на допустимые значения
            for (i in value) {
                if (i !in '0'..'9')
                    errorList.add(ErrorCode.INVALID_PHONE_NUMBER_VALUE_CHARACTER)
            }
            //Проверка на допустимое значение первого символа в поле
            if (value[0] != '7' && value[0] != '8') {
                errorList.add(ErrorCode.INVALID_PHONE_NUMBER_FIRST_VALUE_CHARACTER)
            }
            //Проверка на длину(должна быть равна 11 символам)
            if (value.length != 11) {
                errorList.add(ErrorCode.INVALID_PHONE_NUMBER_MAX_LENGTH)
            }
        } else errorList.add(ErrorCode.INVALID_PHONE_NUMBER_VALUE_IS_NULL)
        return errorList
    }
}

class FirstLastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = arrayListOf<ErrorCode>()
        //Проверяем поле на null
        if (!value.isNullOrEmpty()) {
            //Проверяем поле на допустимые значения
            for (i in value) {
                if (i !in 'А'..'я')
                    errorList.add(ErrorCode.INVALID_FIRST_LAST_NAME_VALUE_CHARACTER)
            }
            //Проверка на длину(не должна превышать больше 16 символов)
            if (value.length > 16) {
                errorList.add(ErrorCode.INVALID_FIRST_LAST_NAME_MAX_LENGTH)
            }
        } else errorList.add(ErrorCode.INVALID_FIRST_LAST_NAME_NUMBER_VALUE_IS_NULL)
        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = arrayListOf<ErrorCode>()
        //Проверяем поле на null
        if (!value.isNullOrEmpty()) {
            //Проверяем поле на допустимые значения
            for (i in value) {
                if (i !in 'A'..'z' && i !in '0'..'9' && i != '.' && i != '@')
                    errorList.add(ErrorCode.INVALID_EMAIL_VALUE_CHARACTER)
            }
            //Проверка на длину(не должна превышать больше 32 символов)
            if (value.length > 32) {
                errorList.add(ErrorCode.INVALID_EMAIL_MAX_LENGTH)
            }
            //Проверка на "имя_домена" общий вариант
//            try {
//                Pattern.compile(
//                    "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
//                            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
//                            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
//                            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
//                ).matcher(value).matches()
//            }catch (e:Exception){
//                errorList.add(ErrorCode.INVALID_EMAIL_VALUE_DOG)
//            }

//            Проверка на "имя_домена" упрощенный(примитивный) вариант.
//         Проверка на содержание в поле символа '@'
            if (!value.contains('@')) {
                errorList.add(ErrorCode.INVALID_EMAIL_VALUE_DOG)
                //Определяем поле после символа "@"
            } else {
                var dogIndex = 0
                for (i in value.indices)
                    if (value[i] == '@') {
                        dogIndex = i
                        break
                    }
                val afterDogString = value.substring(dogIndex + 1)
                //Проверяем первый и последний символ в поле после "@"
                if (afterDogString[0] !in 'a'..'z' || afterDogString[afterDogString.length - 1] !in 'a'..'z') {
                    errorList.add(ErrorCode.INVALID_EMAIL_VALUE_DOG)
                }
                if (!afterDogString.contains('.')) {
                    errorList.add(ErrorCode.INVALID_EMAIL_VALUE_DOG)
                }
            }
        } else errorList.add(ErrorCode.INVALID_EMAIL_VALUE_IS_NULL)
        return errorList
    }
}

class SNILSValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = arrayListOf<ErrorCode>()
        //Проверяем поле на null
        if (!value.isNullOrEmpty()) {
            //Проверяем поле на допустимые значения
            for (i in value) {
                if (i !in '0'..'9')
                    errorList.add(ErrorCode.INVALID_SNILS_CHARACTER)
            }
            //Проверка на длину(должна быть равна 11 символам)
            if (value.length != 11) {
                errorList.add(ErrorCode.INVALID_SNILS_LENGTH)
            }
            //Проверка на Контрольное число
            if (value.takeLast(2).toInt() != getCheckNumber(value)) {
                errorList.add(ErrorCode.INVALID_SNILS_OF_CONTROL_NUMBER)
            }
        } else errorList.add(ErrorCode.INVALID_SNILS_VALUE_IS_NULL)
        return errorList
    }

    private fun getCheckNumber(value: String): Int {
        var checkNumber = 0
        for (i in 0..8) {
            checkNumber += value[i].toString().toInt() * (9 - i)
        }
        if (checkNumber == 100 || checkNumber == 101)
            checkNumber = 0
        if (checkNumber > 101)
            checkNumber %= 101
        return checkNumber
    }
}
