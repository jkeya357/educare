"use client";

import { useState } from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

import { useCreateCourseMutation } from "@/store/courseSlice/CourseApiSlice";
import { getCurrentUserId } from "@/store/authSlice/UserSlice";
import { useSelector } from "react-redux";

export default function CreateCourseModal() {
  const [createCourse, { isLoading, isSuccess }] = useCreateCourseMutation();
  const userId = useSelector(getCurrentUserId);

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState<number>();

  const handleCreate = async () => {
    if (!title || !description) return;

    try {
      await createCourse({
        title,
        courseOwner: userId,
        description,
        price,
      }).unwrap();

      setTitle("");
      setDescription("");
      setPrice(0);
    } catch (error) {
      console.log("Error creating course:", error);
    }
  };

  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button className="text-white bg-gradient-to-r from-green-400 via-green-500 to-green-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-base text-sm px-4 py-2.5 text-center leading-5">
          + Create Course
        </Button>
      </DialogTrigger>

      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>Create New Course</DialogTitle>
        </DialogHeader>

        <div className="space-y-4 pt-2">
          <Input
            placeholder="Course Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          <Input
            placeholder="Course Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />

          <Input
            type="number"
            placeholder="Course Price"
            value={price}
            onChange={(e) => setPrice(Number(e.target.value))}
          />

          <Button
            onClick={handleCreate}
            disabled={isLoading}
            className="w-full"
          >
            {isLoading ? "Creating..." : "Create Course"}
          </Button>

          {isSuccess && (
            <p className="text-green-500 text-sm">
              Course created successfully
            </p>
          )}
        </div>
      </DialogContent>
    </Dialog>
  );
}
