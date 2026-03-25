export interface PaymentResponse{
    paymentId: string,    
    amount: number,
    status: Status
}

export type Status = "COMPLETED" | "PENDING" | "FAILED"