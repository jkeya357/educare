import { educationApi } from "../api/educationApi";
import { createEntityAdapter, createSelector, EntityState } from "@reduxjs/toolkit";
import { CourseResponse } from "@/types/CourseResponse";
import { RootState } from "../store";

export const courseAdapter = createEntityAdapter<CourseResponse, string>({
    selectId: (course) => course.courseId
})

const initialState = courseAdapter.getInitialState()

const CourseApiSlice = educationApi.injectEndpoints({
    endpoints: (builder) => ({
        getCourse: builder.query<EntityState<CourseResponse, string>, void>({
            query: () => "/api/v1/course",
            transformResponse: (responseData: CourseResponse[]) => {
                return courseAdapter.setAll(initialState, responseData)
            },
            providesTags: (result) => {
                if(result?.ids){
                    return [
                        {type: "Course" as const, id: "LIST"},
                        ...result.ids.map(id => ({type: "Course" as const, id}))
                    ]
                }else return [{type: "Course", id: "LIST"}]
            }
        }),
        createCourse: builder.mutation({
            query: (requestBody) => ({
                url: "/api/v1/course",
                method: "POST",
                body: {...requestBody}
            }),
            invalidatesTags: () => [{type: "Course", id: "LIST"}]
        }),
        updateCourseImage: builder.mutation({
            query: ({courseId, file}) => {
                const formData = new FormData()
                formData.append("file", file)


                return{
                    url: `/api/v1/course/s3/upload/${courseId}`,
                    method: "POST",
                    body: formData
                }
            },
            invalidatesTags: () => [{type: "Course", id: "LIST"}]
        })
    })
})

export const {useGetCourseQuery, useCreateCourseMutation , useUpdateCourseImageMutation} = CourseApiSlice

const selectCourseResult = CourseApiSlice.endpoints.getCourse.select()

const selectCourseData = createSelector(
    selectCourseResult,
    (courseResult) => courseResult?.data
)

export const {
    selectAll: selectAllCourses,
    selectById: selectCourseById
} = courseAdapter.getSelectors((state: RootState) => selectCourseData(state) ?? initialState)