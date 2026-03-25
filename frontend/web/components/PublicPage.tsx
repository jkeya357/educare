"use client";

import { Button } from "@/components/ui/button";
import { ThemeProvider } from "next-themes";
import NavBarComponent from "./navbar/NavBarComponent";
import HeroComponent from "./hero/HeroComponent";
import FeaturesComponent from "./features/FeaturesComponent";
import FooterComponent from "./footer/FooterComponent";

function PublicPage() {
  return (
    <div className="min-h-screen bg-background text-foreground">
      {/* Navbar */}

      {/* Hero */}
      <HeroComponent />

      {/* Features */}
      <FeaturesComponent />

      {/* CTA */}
      <section className="py-20 border-t">
        <div className="max-w-4xl mx-auto text-center space-y-6 px-6">
          <h3 className="text-3xl font-bold">
            Start Your Learning Journey Today
          </h3>
          <p className="text-muted-foreground">
            Join thousands of students already improving their skills.
          </p>
          <Button size="lg">
            <a href="/auth/signup">Create Free Account</a>
          </Button>
        </div>
      </section>
    </div>
  );
}

export default function EducationLandingPage() {
  return (
    <ThemeProvider attribute="class" defaultTheme="system" enableSystem>
      <PublicPage />
    </ThemeProvider>
  );
}
