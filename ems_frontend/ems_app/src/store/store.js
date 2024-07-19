import { configureStore } from "@reduxjs/toolkit";
import { EmsService } from "./Apiservice/Ems.service";
import { getDefaultConfig } from "tailwind-merge";


export const store = configureStore({
    reducer: {
        [EmsService.reducerPath]: EmsService.reducer
    },

    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(EmsService.middleware)
})