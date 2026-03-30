"use client";

import { useSelector } from "react-redux";

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";

import {
  useGetAllEnrollmentsQuery,
  selectAll as selectAllEnrollments,
} from "@/store/enrollmentSlice/EnrollmentApiSlice";

import {
  useGetAllUsersQuery,
  selectAllUsers,
} from "@/store/userASlice/UsersApiSlice";

import {
  useGetCourseQuery,
  selectAllCourses,
} from "@/store/courseSlice/CourseApiSlice";

const AdminDashboard = () => {
  useGetAllUsersQuery();
  useGetCourseQuery();
  useGetAllEnrollmentsQuery();

  const foundUsers = useSelector(selectAllUsers);
  const users = foundUsers.filter((user) => user.role === "STUDENT");
  const courses = useSelector(selectAllCourses);
  const enrollments = useSelector(selectAllEnrollments);

  return (
    <div className="space-y-6">
      <div className="grid grid-cols-3 gap-6">
        <Card>
          <CardHeader>
            <CardTitle>Total Users</CardTitle>
          </CardHeader>
          <CardContent>
            <p className="text-2xl font-bold">{users.length}</p>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Total Courses</CardTitle>
          </CardHeader>
          <CardContent>
            <p className="text-2xl font-bold">{courses.length}</p>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Total Enrollments</CardTitle>
          </CardHeader>
          <CardContent>
            <p className="text-2xl font-bold">{enrollments.length}</p>
          </CardContent>
        </Card>
      </div>

      {/* USERS + ENROLLMENTS */}
      <Card>
        <CardHeader>
          <CardTitle>Users & Enrolled Courses</CardTitle>
        </CardHeader>

        <CardContent className="space-y-4">
          {users.map((user) => {
            const userEnrollments = enrollments.filter(
              (enrollment) => enrollment.studentId === user.userId,
            );

            const enrolledCourses = userEnrollments.map((enrollment) =>
              courses.find((course) => course.courseId === enrollment.courseId),
            );

            return (
              <div
                key={user.userId}
                className="border rounded-lg p-4 space-y-2"
              >
                <p className="font-semibold">
                  {user.firstname} {user.lastname}
                </p>

                {enrolledCourses.length === 0 ? (
                  <p className="text-sm text-muted-foreground">
                    Not enrolled in any course
                  </p>
                ) : (
                  <ul className="text-sm list-disc ml-4">
                    {enrolledCourses.map(
                      (course) =>
                        course && <li key={course.courseId}>{course.title}</li>,
                    )}
                  </ul>
                )}
              </div>
            );
          })}
        </CardContent>
      </Card>

      {/* ALL COURSES */}
      <Card>
        <CardHeader>
          <CardTitle>All Available Courses</CardTitle>
        </CardHeader>

        <CardContent>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-4">
            {courses.map((course) => (
              <div key={course.courseId} className="border rounded-lg p-4">
                <p className="font-semibold">{course.title}</p>

                <p className="text-sm text-muted-foreground mt-1">
                  {course.description}
                </p>
              </div>
            ))}
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default AdminDashboard;
