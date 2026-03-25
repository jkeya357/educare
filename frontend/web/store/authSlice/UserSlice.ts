import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "@/store/store";

interface AuthState{
    token: string | null
    user: {
        id: string,
        username: string,
        role: string
    } | null
}

const initialState: AuthState = {
    token: null,
    user: null
}

const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        setCredentials: (
            state, 
            action: PayloadAction<{
                token: string, user: {userId: string, username: string, role: string}
            }>
        ) => {
            const {token, user} = action.payload
            state.token = token
            state.user = {
                id: user.userId,
                username: user.username,
                role: user.role
            }
        },
        logout: (state) => {
            state.token = null
            state.user = null
        }
    }
})

export const {setCredentials, logout} = authSlice.actions

export default authSlice.reducer

export const getAccessToken = (state: RootState) => state.auth.token

export const getCurrentUserId = (state:RootState) => state.auth.user?.id

export const getCurrentUsername = (state: RootState) => state.auth.user?.username

export const getUserRole = (state: RootState) => state.auth.user?.role