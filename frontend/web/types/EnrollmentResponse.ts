export interface EnrollmentResponse{
    enrollmentId: string,
    courseId: string,
    studentId: string,
    status: Status,
    enrollmentDate: Date
}

export type Status = "ENROLLED" | "PENDING" | "UNSUCCESSFUL"