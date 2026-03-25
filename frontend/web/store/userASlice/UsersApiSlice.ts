import { UserResponse } from "@/types/UserResponse";
import { createEntityAdapter, createSelector, EntityState } from "@reduxjs/toolkit";
import { educationApi } from "../api/educationApi";
import { RootState } from "../store";

const userAdapter = createEntityAdapter<UserResponse, string>({
    selectId: (user) => user.userId
})

const initialState = userAdapter.getInitialState()


const UsersApiSlice = educationApi.injectEndpoints({
    endpoints: (builder) => ({
        getAllUsers: builder.query<EntityState<UserResponse, string>, void>({
            query: () => "/api/v1/auth/users",
            transformResponse: (response: UserResponse[]) => {
                console.log("API RESPONSE: ", response)
                return userAdapter.setAll(initialState, response)
            },
            providesTags: (result, error, id) => {
                if(result?.ids){
                    return[
                        {type: "User" as const, id: "LIST"},
                        ...result.ids.map(id => ({type: "User" as const, id}))
                    ]
                }else return [{type: "User", id: "LIST"}]
            }
        }),
        getUserById: builder.query<UserResponse, string>({
            query: (userId) => `/api/v1/auth/users/id?userId=${userId}`,
            providesTags: (result, error, id) => [{type: "User", id}]
        })
    })
})

export const {useGetAllUsersQuery,useGetUserByIdQuery} = UsersApiSlice

const selectUserResult = UsersApiSlice.endpoints.getAllUsers.select()

const selectUserData = createSelector(
    selectUserResult,
    (userResult)=> userResult?.data
)

export const {
    selectById: selectUsersById,
    selectAll: selectAllUsers
} = userAdapter.getSelectors((state: RootState) => selectUserData(state) ?? initialState)
