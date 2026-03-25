import { educationApi } from "../api/educationApi";
import { createEntityAdapter, createSelector, EntityState } from "@reduxjs/toolkit";
import { EnrollmentResponse } from "@/types/EnrollmentResponse";
import { RootState } from "../store";

const enrollmentAdapter = createEntityAdapter<EnrollmentResponse, string>({
    selectId: (enroll) => enroll.enrollmentId
})

const initialState = enrollmentAdapter.getInitialState()

const EnrollmentApiSlice = educationApi.injectEndpoints({
    endpoints: (builder) => ({

        getAllEnrollments: builder.query<EntityState<EnrollmentResponse, string>, void>({
            query: () => "/api/v1/enrollment/students",
            transformResponse: (response: EnrollmentResponse[]) => {
                return enrollmentAdapter.setAll(initialState, response)
            },
            providesTags: (result, error, id) => {
                if(result?.ids){
                    return[
                        {type: "Enrollment" as const, id: "LIST"},
                        ...result.ids.map(id => ({type: "Enrollment" as const, id}))
                    ]
                }else return [{type: "Enrollment", id: "LIST"}]
            }
        }),
        getEnrollments: builder.query<EntityState<EnrollmentResponse, string>, string>({
            query: (studentId) => `/api/v1/enrollment/student?studentId=${studentId}`,
            transformResponse: (response: EnrollmentResponse[]) => {
                return enrollmentAdapter.setAll(initialState, response)
            },
            providesTags: (result) => {
                if(result?.ids){
                    return[
                        {type: "Enrollment" as const, id: "LIST"},
                        ...result.ids.map(id => ({type: "Enrollment" as const, id}))
                    ]
                }else return [{type: "Enrollment", id: "LIST"}]
            }
        }),
        createEnrollment: builder.mutation({
            query: (requestBody) => ({
                url: "/api/v1/enrollment",
                method: "POST",
                body: {...requestBody}
            }),
            invalidatesTags: () => [{type: "Enrollment", id: "LIST"}]
        })
    })
})

export const {useGetAllEnrollmentsQuery,useGetEnrollmentsQuery, useCreateEnrollmentMutation} = EnrollmentApiSlice

const selectEnrollmentResult =
  EnrollmentApiSlice.endpoints.getAllEnrollments.select() as (state: any) => {
    data: EntityState<EnrollmentResponse, string> | undefined
  };

export const selectAllEnrollments = createSelector(
    selectEnrollmentResult,
    (result) => result.data
)

export const selectEnrollmentsByStudent = (studentId: string) =>
  createSelector(
    EnrollmentApiSlice.endpoints.getEnrollments.select(studentId),
    (result) => result?.data ?? initialState
  );

export const {
    selectAll: selectAll
} = enrollmentAdapter.getSelectors(state => selectAllEnrollments(state) ?? initialState)

export const selectAllEnrollmentsByStudent = (studentId: string) =>
  createSelector(
    selectEnrollmentsByStudent(studentId),
    (state) => enrollmentAdapter.getSelectors().selectAll(state)
  );

