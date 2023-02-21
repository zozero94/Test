package zero.data.source.remote

import retrofit2.http.GET
import zero.data.model.Rule

interface TestApi {
    @GET("ZeroFriends/GoStopCalculator/develop/app/src/main/assets/rule.json")
    suspend fun getRule(): List<Rule>
}