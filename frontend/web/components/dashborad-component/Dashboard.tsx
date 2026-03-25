"use client";

import React from "react";
import { useSelector } from "react-redux";
import { skipToken } from "@reduxjs/toolkit/query";
import { getCurrentUserId } from "@/store/authSlice/UserSlice";
import { getUserRole } from "@/store/authSlice/UserSlice";
import AdminDashboard from "./AdminDashboard";
import StudentDashboard from "./StudentDashboard";

import { useGetUserByIdQuery } from "@/store/userASlice/UsersApiSlice";

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { Badge } from "@/components/ui/badge";

const Dashboard = () => {
  const userId = useSelector(getCurrentUserId);

  const userRole = useSelector(getUserRole);

  const { data: user, isLoading: userLoading } = useGetUserByIdQuery(
    userId ?? skipToken,
  );

  return (
    <div className="p-6 space-y-6">
      {/* PROFILE */}
      <Card>
        <CardHeader>
          <CardTitle>Profile</CardTitle>
        </CardHeader>

        <CardContent className="flex items-center gap-4">
          <Avatar className="h-14 w-14">
            <AvatarFallback>
              {user?.firstname?.[0]}
              {user?.lastname?.[0]}
            </AvatarFallback>
          </Avatar>

          <div>
            <p className="text-lg font-semibold">
              {user?.firstname} {user?.lastname}
            </p>

            <p className="text-sm text-muted-foreground">{user?.email}</p>

            <Badge className="mt-2">{user?.role}</Badge>
          </div>
        </CardContent>
      </Card>

      {/* STATS */}
      {userRole === "ADMIN" ? (
        <AdminDashboard />
      ) : (
        <StudentDashboard userLoading={userLoading} userId={userId} />
      )}
    </div>
  );
};

export default Dashboard;
