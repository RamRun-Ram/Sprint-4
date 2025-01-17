import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation error`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_FIRST_LAST_NAME_MAX_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_FIRST_LAST_NAME_NUMBER_VALUE_IS_NULL)
        assertEquals(exception.errorCode.size, 2)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

    @Test
    fun `validation error-phone`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_NUMBER_FIRST_VALUE_CHARACTER))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_NUMBER_VALUE_CHARACTER))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_NUMBER_MAX_LENGTH))
    }

    @Test
    fun `validation error-error on null`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_FIRST_LAST_NAME_NUMBER_VALUE_IS_NULL))
    }

    @Test
    fun `validation error-the domain name is missing`() {
        val client = getClientFromJson("/fail/user_with_error_value_dog.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_EMAIL_VALUE_DOG))
    }

    @Test
    fun `validation error-the control number`() {
        val client = getClientFromJson("/fail/user_with_error_control_number.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_SNILS_OF_CONTROL_NUMBER))
    }
}