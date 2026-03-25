import {
  useGetCourseQuery,
  selectAllCourses,
} from "@/store/courseSlice/CourseApiSlice";
import { getUserRole } from "@/store/authSlice/UserSlice";
import { useSelector } from "react-redux";
import { Input } from "@/components/ui/input";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { useState } from "react";
import ImageUpload from "@/components/home/UpdateImage";
import CourseInfo from "../course_card/CourseInfo";
import CreateCourseModal from "../course_card/CreateCourseModel";

const HomeComponent = () => {
  const { isLoading, isError } = useGetCourseQuery();
  const courses = useSelector(selectAllCourses);

  const [search, setSearch] = useState("");

  const filteredCourses = courses?.filter((course: any) =>
    course.title.toLowerCase().includes(search.toLowerCase()),
  );

  const userRole = useSelector(getUserRole);

  if (isLoading) {
    return (
      <div className="flex justify-center items-center h-[60vh]">
        <p className="text-muted-foreground">Loading courses...</p>
      </div>
    );
  }

  if (isError) {
    return (
      <div className="flex justify-center items-center h-[60vh]">
        <p className="text-red-500">Failed to load courses.</p>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto px-6 py-10 space-y-8">
      {/* Header */}
      <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
        <div>
          <h1 className="text-3xl font-bold">Explore Courses</h1>
          <p className="text-muted-foreground">
            Discover new skills and grow your knowledge.
          </p>
        </div>

        {/* Search */}
        <Input
          placeholder="Search courses..."
          className="max-w-sm"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>

      {userRole === "ADMIN" && (
        <div className="flex justify-end">
          <CreateCourseModal />
        </div>
      )}

      {/* Courses Grid */}
      {filteredCourses?.length === 0 ? (
        <div className="text-center py-20 text-muted-foreground">
          No courses found.
        </div>
      ) : (
        <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
          {filteredCourses?.map((course: any) => (
            <Card
              key={course.id}
              className="hover:shadow-xl transition-shadow duration-300 cursor-pointer group"
            >
              <div className="h-40 w-full bg-muted rounded-t-lg overflow-hidden">
                <img
                  src={course.imageUrl || "/placeholder-course.jpg"}
                  alt={course.title}
                  className="w-full h-full object-cover group-hover:scale-105 transition-transform"
                />
              </div>

              <CardHeader>
                <CardTitle className="text-lg line-clamp-2">
                  {course.title}
                </CardTitle>
              </CardHeader>

              <CardContent className="space-y-3">
                <CourseInfo course={course} userRole={userRole} />
              </CardContent>

              {userRole === "ADMIN" && (
                <ImageUpload courseId={course.courseId} />
              )}
            </Card>
          ))}
        </div>
      )}
    </div>
  );
};

export default HomeComponent;
