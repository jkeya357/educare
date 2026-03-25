"use client";

import React from "react";
import {
  useGetCourseQuery,
  selectAllCourses,
} from "@/store/courseSlice/CourseApiSlice";
import {
  useGetEnrollmentsQuery,
  selectAllEnrollmentsByStudent,
} from "@/store/enrollmentSlice/EnrollmentApiSlice";
import { skipToken } from "@reduxjs/toolkit/query";
import { useSelector } from "react-redux";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { EnrollmentResponse } from "@/types/EnrollmentResponse";

interface Prop {
  userId: string | undefined;
  userLoading: boolean;
}

const StudentDashboard = ({ userId, userLoading }: Prop) => {
  const { isLoading: coursesLoading } = useGetCourseQuery();
  const { data: enrollmentData, isLoading: enrollLoading } =
    useGetEnrollmentsQuery(userId ?? skipToken);

  const coursesInDB = useSelector(selectAllCourses);

  const enrollments: EnrollmentResponse[] = useSelector(
    userId ? selectAllEnrollmentsByStudent(userId) : () => [],
  );

  const enrolledCourseIds = enrollments.map((e) => e.courseId);

  const enrolledCoursesInDB = coursesInDB.filter((course) =>
    enrolledCourseIds.includes(course.courseId),
  );

  if (userLoading || enrollLoading || coursesLoading) {
    return <div className="p-6">Loading dashboard...</div>;
  }

  return (
    <>
      <Card>
        <CardHeader>
          <CardTitle>Learning Stats</CardTitle>
        </CardHeader>

        <CardContent className="flex gap-6">
          <div>
            <p className="text-2xl font-bold">{enrollments.length}</p>
            <p className="text-sm text-muted-foreground">Courses Enrolled</p>
          </div>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle>My Courses</CardTitle>
        </CardHeader>

        <CardContent>
          {enrolledCoursesInDB.length === 0 ? (
            <p className="text-sm text-muted-foreground">
              You haven't enrolled in any courses yet.
            </p>
          ) : (
            <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-4">
              {enrolledCoursesInDB.map((course) => {
                const enrollment = enrollments.find(
                  (e) => e.courseId === course.courseId,
                );

                return (
                  <div
                    key={course.courseId}
                    className="border rounded-lg p-4 hover:shadow transition"
                  >
                    <p className="font-semibold">{course.title}</p>

                    <p className="text-xs text-muted-foreground mt-2">
                      Enrolled on{" "}
                      {enrollment
                        ? new Date(
                            enrollment.enrollmentDate,
                          ).toLocaleDateString()
                        : "N/A"}
                    </p>
                  </div>
                );
              })}
            </div>
          )}
        </CardContent>
      </Card>
    </>
  );
};

export default StudentDashboard;
