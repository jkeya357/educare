import React from "react";

const LoadingProvider = () => {
  return (
    <div className="flex items-center justify-center min-h-screen bg-background">
      <div className="h-10 w-10 animate-spin rounded-full border-4 border-muted border-t-primary"></div>
    </div>
  );
};

export default LoadingProvider;
