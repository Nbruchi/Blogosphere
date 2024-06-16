import HomeHeader from "@/components/shared/HomeHeader";
import Image from "next/image";
import React from "react";

const LadindPage = () => {
  return (
    <section className="w-full h-screen">
      <HomeHeader />
      <div className="flex w-full">
        <div className="flex-1">
          <Image
            src="/social-media.jpg"
            width={800}
            height={800}
            alt="social media"
            className="w-full h-full object-cover"
          />
        </div>
        <div className="flex-1">
          <div>
            <h1>Blogosphere</h1>
            <p>Wider communities are built from cooperation and trust.</p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default LadindPage;
