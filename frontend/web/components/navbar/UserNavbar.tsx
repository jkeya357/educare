import { Button } from "@/components/ui/button";
import { LogOut } from "lucide-react";
import { logout } from "@/store/authSlice/UserSlice";
import { useDispatch } from "react-redux";
import Link from "next/link";
import { useRouter } from "next/navigation";

const UserNavbar = ({ user }: { user: string }) => {
  const dispatch = useDispatch();
  const router = useRouter();

  const handleLogout = () => {
    dispatch(logout());
    router.push("/");
  };

  return (
    <>
      <span className="text-sm font-medium">{user}</span>

      <Button variant="ghost">
        <Link href="/dashboard">Dashboard</Link>
      </Button>

      <Button variant="destructive" onClick={handleLogout}>
        <LogOut className="w-4 h-4 mr-2" />
        Logout
      </Button>
    </>
  );
};

export default UserNavbar;
