import { Button } from "@/components/ui/button";
import Link from "next/link";

const GuestNavbar = () => {
  return (
    <>
      <Button variant="ghost">
        <Link href="/auth/signin">Login</Link>
      </Button>

      <Button>
        <Link href="/auth/signup">Get Started</Link>
      </Button>
    </>
  );
};

export default GuestNavbar;
