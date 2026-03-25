"use client";
import { ComponentType } from "react";
import {
  getAccessToken,
  getCurrentUsername,
} from "@/store/authSlice/UserSlice";
import Link from "next/link";
import { useSelector } from "react-redux";
import { Button } from "@/components/ui/button";
import UserNavbar from "./UserNavbar";
import GuestNavbar from "./GuestNavbar";
import { useTheme } from "next-themes";
import { Sun, Moon } from "lucide-react";

type NavBarProps = {
  ThemeToggle: ComponentType;
};

function ThemeToggle() {
  const { theme, setTheme } = useTheme();

  return (
    <Button
      variant="outline"
      size="icon"
      onClick={() => setTheme(theme === "dark" ? "light" : "dark")}
    >
      {theme === "dark" ? <Sun size={18} /> : <Moon size={18} />}
    </Button>
  );
}

const NavBarComponent = () => {
  const user = useSelector(getCurrentUsername);
  const accessToken = useSelector(getAccessToken);

  return (
    <header className="w-full border-b">
      <div className="max-w-7xl mx-auto flex items-center justify-between p-4">
        <h1 className="text-xl font-bold">
          <Link href={accessToken ? "/home" : "/"}>EduPlatform</Link>
        </h1>

        <ThemeToggle />

        <div className="flex items-center gap-3">
          {accessToken && user ? <UserNavbar user={user} /> : <GuestNavbar />}
        </div>
      </div>
    </header>
  );
};

export default NavBarComponent;
