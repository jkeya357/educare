"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useSignInMutation } from "@/store/authSlice/AuthApiSlice";
import { useDispatch } from "react-redux";
import { setCredentials } from "@/store/authSlice/UserSlice";
import { useRouter } from "next/navigation";

export default function SignInComponent() {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const [signin, { isLoading, isSuccess }] = useSignInMutation();

  const dispatch = useDispatch();
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const res = await signin({
        email,
        password,
      }).unwrap();

      dispatch(
        setCredentials({
          token: res.token,
          user: {
            userId: res.userId,
            username: res.username,
            role: res.role,
          },
        }),
      );

      if (res.token) {
        router.push("/home");
      }

      console.log("Signin successful", res);
    } catch (error) {
      console.error("Signin failed", error);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen px-4">
      <Card className="w-full max-w-md">
        <CardHeader>
          <CardTitle>Create your account</CardTitle>
        </CardHeader>

        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <Label htmlFor="email">Email</Label>
              <Input
                name="email"
                type="email"
                placeholder="john@email.com"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>

            <div>
              <Label htmlFor="password">Password</Label>
              <Input
                name="password"
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>

            <Button type="submit" className="w-full" disabled={isLoading}>
              {isLoading ? "Signing account..." : "Sign In"}
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
}
