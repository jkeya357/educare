import React from "react";

const FooterComponent = () => {
  return (
    <footer className="border-t py-6">
      <div className="max-w-7xl mx-auto px-6 flex justify-between text-sm text-muted-foreground">
        <p>© 2026 EduPlatform</p>
        <div className="flex gap-4">
          <span>Privacy</span>
          <span>Terms</span>
          <span>Contact</span>
        </div>
      </div>
    </footer>
  );
};

export default FooterComponent;
