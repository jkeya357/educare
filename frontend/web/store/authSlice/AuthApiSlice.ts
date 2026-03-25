import { educationApi } from "../api/educationApi";
import { CreateUserRequest } from "@/types/CreateUserRequest";
import { UserLoginRequest } from "@/types/UserLoginRequest";

const authApiSlice = educationApi.injectEndpoints({
    endpoints: (builder) => ({
        signUp: builder.mutation({
            query: (requestsBody: CreateUserRequest) => ({
                url: "/api/v1/auth/signup",
                method: "Post",
                body: {...requestsBody}
            }),
            invalidatesTags: () => [{type: "User", id: "LIST"}]
        }),
        signIn: builder.mutation({
            query: (requestBody: UserLoginRequest) => ({
                url: "/api/v1/auth/signin",
                method: "POST",
                body: {...requestBody}
            })
        })
    })
})

export const {useSignInMutation, useSignUpMutation} = authApiSlice