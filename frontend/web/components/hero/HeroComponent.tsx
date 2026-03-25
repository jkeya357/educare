import { Button } from "@/components/ui/button";
import { Card, CardTitle } from "@/components/ui/card";
import { BookOpen, GraduationCap, Users, Laptop } from "lucide-react";

const HeroComponent = () => {
  return (
    <section className="max-w-7xl mx-auto px-6 py-20 grid md:grid-cols-2 gap-10 items-center">
      <div className="space-y-6">
        <h2 className="text-4xl md:text-5xl font-bold leading-tight">
          Learn Faster With Modern Online Education
        </h2>
        <p className="text-muted-foreground text-lg">
          Access high quality courses, track your progress, and build real world
          skills from industry professionals.
        </p>
        <div className="flex gap-4">
          <Button size="lg">
            <a href="/auth/signup">Start Learning</a>
          </Button>
          <Button size="lg" variant="outline">
            Browse Courses
          </Button>
        </div>
      </div>

      <div className="grid grid-cols-2 gap-4">
        <Card className="p-4">
          <BookOpen className="mb-3" />
          <CardTitle>500+ Courses</CardTitle>
        </Card>
        <Card className="p-4">
          <GraduationCap className="mb-3" />
          <CardTitle>Expert Instructors</CardTitle>
        </Card>
        <Card className="p-4">
          <Users className="mb-3" />
          <CardTitle>Community Learning</CardTitle>
        </Card>
        <Card className="p-4">
          <Laptop className="mb-3" />
          <CardTitle>Learn Anywhere</CardTitle>
        </Card>
      </div>
    </section>
  );
};

export default HeroComponent;
