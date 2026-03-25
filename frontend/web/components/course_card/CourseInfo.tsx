import { Button } from "@/components/ui/button";
import {
  selectAllEnrollmentsByStudent,
  useCreateEnrollmentMutation,
  useGetEnrollmentsQuery,
} from "@/store/enrollmentSlice/EnrollmentApiSlice";
import {
  useGetAllUsersQuery,
  selectUsersById,
} from "@/store/userASlice/UsersApiSlice";
import { getCurrentUserId } from "@/store/authSlice/UserSlice";
import { useSelector } from "react-redux";
import { useState } from "react";
import { RootState } from "@/store/store";
import { skipToken } from "@reduxjs/toolkit/query";

type Course = {
  courseId: string;
  title: string;
  description: string;
  courseOwner: string;
  price?: number;
  imageUrl?: string;
};

type Props = {
  course: Course;
  userRole: string | undefined;
};

const CourseInfo = ({ course, userRole }: Props) => {
  useGetAllUsersQuery();
  const [enroll, { isLoading }] = useCreateEnrollmentMutation();

  const userId = useSelector(getCurrentUserId);

  useGetEnrollmentsQuery(userId ?? skipToken);

  const enrollments = useSelector(
    userId ? selectAllEnrollmentsByStudent(userId) : () => [],
  );

  const [enrollmentResponse, setEnrollmentResponse] = useState<any>(null);

  const instructor = useSelector((state: RootState) =>
    selectUsersById(state, course.courseOwner),
  );

  const isEnrolled = enrollments.some(
    (enrollment) => enrollment.courseId === course.courseId,
  );

  const handleEnrollment = async () => {
    try {
      const res = await enroll({
        studentId: userId,
        courseId: course.courseId,
      }).unwrap();

      setEnrollmentResponse(res);
    } catch (error) {
      console.log("Error enrolling to course: ", error);
    }
  };

  return (
    <>
      <p className="text-sm text-muted-foreground line-clamp-2">
        {course.description}
      </p>

      <p className="text-sm font-medium">
        Instructor:{" "}
        {instructor
          ? `${instructor.firstname} ${instructor.lastname}`
          : "Unknown"}
      </p>

      <div className="flex items-center justify-between pt-2">
        <span className="text-primary font-semibold">
          {course.price ? `$${course.price}` : "Free"}
        </span>

        <Button size="sm">View</Button>
      </div>

      <Button
        onClick={handleEnrollment}
        disabled={isLoading || isEnrolled}
        className={`font-medium rounded-base text-sm px-4 py-2.5 text-center leading-5 text-white
        ${
          isEnrolled
            ? "bg-gray-400 cursor-not-allowed"
            : "bg-gradient-to-r from-green-400 via-green-500 to-green-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800"
        }`}
      >
        {isLoading ? "Enrolling..." : isEnrolled ? "Enrolled ✓" : "Enroll Now"}
      </Button>

      {/* ADMIN CONTROLS */}
      {userRole === "ADMIN" && (
        <div className="flex gap-2 pt-3 border-t">
          <Button size="sm" variant="outline">
            Edit
          </Button>

          <Button size="sm" variant="destructive">
            Delete
          </Button>
        </div>
      )}
    </>
  );
};

export default CourseInfo;
