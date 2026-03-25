import { educationApi } from "../api/educationApi";
import { createEntityAdapter, createSelector, EntityState } from "@reduxjs/toolkit";
import { PaymentResponse } from "@/types/PaymentResponse";

const paymentAdapter = createEntityAdapter<PaymentResponse, string>({
    selectId: (pay) => pay.paymentId
})
const intitialState = paymentAdapter.getInitialState()

const PaymentApiSlice = educationApi.injectEndpoints({
    endpoints: builder => ({
        createPayment: builder.mutation({
            query: (requestBody) => ({
                url: "/api/v1/payment",
                method: "POST",
                body: {requestBody}
            }),
            invalidatesTags: () => [{type: "Payment", id:"LIST"}]
        }),
        getPayment: builder.query<EntityState<PaymentResponse, string>, void>({
            query: (userId) => `/api/v1/payment?userId=${userId}`,
            transformResponse: (response: PaymentResponse[]) => {
                return paymentAdapter.setAll(intitialState, response)
            },
            providesTags: (result) => {
                if(result?.ids){
                    return[
                        {type: "Payment" as const, id: "LIST"},
                        ...result.ids.map(id => ({type: "Payment" as const, id}))
                    ]
                }else return [{type: "Payment", id: "LIST"}]
            }
        })
    })
})

export const {useCreatePaymentMutation, useGetPaymentQuery} = PaymentApiSlice

const PaymentResult = PaymentApiSlice.endpoints.getPayment.select() as (state: any) => {
    data: EntityState<PaymentResponse, string> | undefined 
}

const PaymentData = createSelector(
    PaymentResult,
    PaymentData => PaymentData?.data
)

export const {
    selectAll: selectAllPayments,
    selectById: selectPaymentById
} = paymentAdapter.getSelectors(state => PaymentData(state) ?? intitialState)