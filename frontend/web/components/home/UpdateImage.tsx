import { useState } from "react";
import { useUpdateCourseImageMutation } from "@/store/courseSlice/CourseApiSlice";

type Props = {
  courseId: string;
};

export default function ImageUpload({ courseId }: Props) {
  const [image, setImage] = useState<File | null>(null);

  const [updateImage, { isLoading, isSuccess }] =
    useUpdateCourseImageMutation();

  console.log("courseId: ", courseId);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) return;
    setImage(e.target.files[0]);
  };

  const handleUpload = async () => {
    if (!image) return;

    await updateImage({
      courseId,
      file: image,
    });
  };

  return (
    <div className="flex flex-col gap-3 mt-4">
      {/* Select Image */}
      <label className="flex items-center justify-center w-full px-4 py-2 text-sm font-medium border border-dashed rounded-lg cursor-pointer bg-muted hover:bg-muted/70 transition">
        {image ? image.name : "Select Course Image"}
        <input
          type="file"
          accept="image/*"
          onChange={handleFileChange}
          className="hidden"
        />
      </label>

      {/* Upload Button */}
      <button
        disabled={!image || isLoading}
        onClick={handleUpload}
        className="px-4 py-2 rounded-lg text-white font-medium bg-primary hover:bg-primary/90 transition disabled:opacity-50 disabled:cursor-not-allowed"
      >
        {isLoading ? "Uploading..." : "Upload Image"}
      </button>

      {/* Success Message */}
      {isSuccess && (
        <p className="text-sm text-green-500 font-medium">
          Image uploaded successfully
        </p>
      )}
    </div>
  );
}
