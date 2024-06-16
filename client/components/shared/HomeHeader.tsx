import Image from 'next/image'
import Link from 'next/link'
import React from 'react'

const HomeHeader = () => {
  return (
    <header className='px-4 py-2 w-full static top-0 left-0 flex items-center justify-between bg-purple-100'>
        <Image src="/logo.png" alt='logo' width={80} height={80}/>
        <nav className='flex flex-row items-center gap-4'>
            <Link href="/sign-in" className='text-purple-800 border border-purple-800 rounded-lg px-8 py-4 hover:bg-purple-800 hover:text-white transform-colors duration-200'>Sign in</Link>
            <Link href="/sign-up" className='bg-purple-800 text-white px-8 py-4 rounded-lg hover:bg-purple-100 hover:text-purple-800 text-lg transition-colors duration-200 hover:border hover:border-purple-800'>Register</Link>
        </nav>
    </header>
  )
}

export default HomeHeader