package webservice;

import retrofit2.http.POST;

public interface Services {
    @POST()
    Call<>
//    'POST', '/jogos/{nome}', 'postCollection',
            'GET',  '/jogos/{nome}/existe/{min:\d+}/{max:\d+}/', 'getChecarRange',
            'GET',  '/jogos/{nome}/existe/{dataini}/{datafim}/', 'getChecarData',
//            'GET',  '/jogos', 'getJogosDisponiveis',
}
