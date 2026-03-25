import {
  Card,
  CardContent,
  CardHeader,
  CardTitle,
  CardDescription,
} from "@/components/ui/card";

const FeaturesComponent = () => {
  return (
    <section className="max-w-7xl mx-auto px-6 py-20">
      <div className="text-center mb-12">
        <h3 className="text-3xl font-bold">Why Choose Our Platform</h3>
        <p className="text-muted-foreground mt-3">
          Everything you need to learn efficiently in one place.
        </p>
      </div>

      <div className="grid md:grid-cols-3 gap-6">
        <Card>
          <CardHeader>
            <CardTitle>Interactive Lessons</CardTitle>
            <CardDescription>
              Hands-on exercises and real-world projects.
            </CardDescription>
          </CardHeader>
          <CardContent>
            Improve your knowledge by practicing with interactive learning
            modules designed by professionals.
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Progress Tracking</CardTitle>
            <CardDescription>Monitor your learning journey.</CardDescription>
          </CardHeader>
          <CardContent>
            Stay motivated by tracking your course completion and achievements.
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Certificates</CardTitle>
            <CardDescription>Showcase your achievements.</CardDescription>
          </CardHeader>
          <CardContent>
            Earn certificates that demonstrate your skills and knowledge.
          </CardContent>
        </Card>
      </div>
    </section>
  );
};

export default FeaturesComponent;
