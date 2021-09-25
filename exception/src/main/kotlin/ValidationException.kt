class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE_NUMBER_VALUE_IS_NULL(100, "Ошибка.Поле -phone- пустое"),
    INVALID_PHONE_NUMBER_VALUE_CHARACTER(101,"Ошибка.В поле -phone- разрешается вводить только цифры"),
    INVALID_PHONE_NUMBER_FIRST_VALUE_CHARACTER(102,"Ошибка.Поле -phone- может начинаться только с 7 или 8ки"),
    INVALID_PHONE_NUMBER_MAX_LENGTH(103,"Ошибка.Поле -phone- превышает допустимое количество символов"),

    INVALID_FIRST_LAST_NAME_VALUE_CHARACTER(104,"Ошибка.В поле -firstName- и -lastName- разрешается вводить только кириллицу"),
    INVALID_FIRST_LAST_NAME_MAX_LENGTH(105,"Ошибка.В поле -firstName- и -lastName- разрешается вводить максимально 16 символов"),
    INVALID_FIRST_LAST_NAME_NUMBER_VALUE_IS_NULL(106,"Ошибка.Поле firstName- и -lastName- не должно быть пустым"),

    INVALID_EMAIL_VALUE_CHARACTER(107,"Ошибка.В поле -EMAIL- разрешается вводить только латиницу"),
    INVALID_EMAIL_VALUE_DOG(108,"Ошибка @имя_домена в поле -EMAIL-"),
    INVALID_EMAIL_MAX_LENGTH(109,"Ошибка.В поле -EMAIL- разрешается вводить максимально 32 символа"),
    INVALID_EMAIL_VALUE_IS_NULL(110,"Ошибка.Поле -EMAIL- не должно быть пустым"),

    INVALID_SNILS_CHARACTER(111,"Ошибка.В поле -snils- разрешается вводить только цифры"),
    INVALID_SNILS_LENGTH(112,"Ошибка.Поле -snils- превышает допустимое количество символов"),
    INVALID_SNILS_OF_CONTROL_NUMBER(113,"Ошибка.В поле -snils- недопустимый символ контрольного числа"),
    INVALID_SNILS_VALUE_IS_NULL(114,"Ошибка.Поле -snils- не должно быть пустым")
}