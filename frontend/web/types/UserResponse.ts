export interface UserResponse{
    userId: string,
    username: string,
    email: string,
    firstname: string,
    lastname: string,
    role: Role,
    createdDate: Date
}

type Role = "STUDENT" | "ADMIN" | "LECTURER"