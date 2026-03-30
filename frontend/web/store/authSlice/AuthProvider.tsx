"use client";

import { useRefreshMutation } from "@/store/authSlice/AuthApiSlice";
import { setCredentials } from "./UserSlice";
import { useDispatch, UseDispatch } from "react-redux";
import { ReactNode, useEffect, useState } from "react";
import LoadingProvider from "./LoadingProvider";

const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [refresh] = useRefreshMutation();
  const dispatch = useDispatch();
  const [initializing, setInitializing] = useState(true);

  useEffect(() => {
    const init = async () => {
      try {
        const res = await refresh().unwrap();
        console.log("INITIAL REFRESH: ", res);
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
      } catch (error) {
        console.log("Global refresh failed");
      } finally {
        setInitializing(false);
      }
    };

    init();
  }, []);

  if (initializing) return <LoadingProvider />;

  return <div>{children}</div>;
};

export default AuthProvider;
