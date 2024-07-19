import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";


export const EmsService = createApi({
    reducerPath: "queryReducers",
    baseQuery: fetchBaseQuery({
        baseUrl: "http://localhost:8080/"
    }),

    tagTypes: ["/employee"],
    endpoints: (builder) => ({})
})