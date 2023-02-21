package zero.data.model

@kotlinx.serialization.Serializable
data class Rule(
    val name: String,
    val isEssential: Boolean,
    val script: String
)
