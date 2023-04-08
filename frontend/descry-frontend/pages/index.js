import Head from 'next/head'

import Image from 'next/image'
import code1 from '../public/code1.svg'
import code2 from '../public/code2.svg'
import code3 from '../public/code3.svg'
import code4 from '../public/code4.svg'
import code5 from '../public/code5.svg'
import code6 from '../public/code6.svg'

import {Inter} from 'next/font/google'
import styles from '@/styles/Home.module.css'

const inter = Inter({subsets: ['latin']})

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Algorithms Visualized</title>
                <meta name="description" content="A collection of intuitive visualizations of algorithms."/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                {/* <link rel="icon" href="/favicon.ico" /> */}
            </Head>
            <main className={styles.main}>
                <div className={styles.navbar}>
                    <div className={styles.title}>
                        <h1>Descry</h1>
                    </div>
                    <div className={styles.subtitle}>
                        <h2>Algorithms Visualized</h2>
                    </div>
                </div>

                <div className={styles.algorithms}>
                    <AlgorithmBox name={"QuickSort"} image={code1}/>
                    <AlgorithmBox name={"MergeSort"} image={code2}/>
                    <AlgorithmBox name={"Binary Search"} image={code3}/>
                    <AlgorithmBox name={"Insertion Sort"} image={code4}/>
                    <AlgorithmBox name={"Dijkstra's Algorithm"} image={code5}/>
                    <AlgorithmBox name={"Depth First Search"} image={code6}/>
                </div>

                <div className={styles.grid}>
                    <a
                        href="https://nextjs.org/docs?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
                        className={styles.card}
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        <h2 className={inter.className}>
                            Docs <span>-&gt;</span>
                        </h2>
                        <p className={inter.className}>
                            Find in-depth information about Next.js features and&nbsp;API.
                        </p>
                    </a>

                    <a
                        href="https://nextjs.org/learn?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
                        className={styles.card}
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        <h2 className={inter.className}>
                            Learn <span>-&gt;</span>
                        </h2>
                        <p className={inter.className}>
                            Learn about Next.js in an interactive course with&nbsp;quizzes!
                        </p>
                    </a>

                    <a
                        href="https://vercel.com/templates?framework=next.js&utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
                        className={styles.card}
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        <h2 className={inter.className}>
                            Templates <span>-&gt;</span>
                        </h2>
                        <p className={inter.className}>
                            Discover and deploy boilerplate example Next.js&nbsp;projects.
                        </p>
                    </a>

                    <a
                        href="https://vercel.com/new?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
                        className={styles.card}
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        <h2 className={inter.className}>
                            Deploy <span>-&gt;</span>
                        </h2>
                        <p className={inter.className}>
                            Instantly deploy your Next.js site to a shareable URL
                            with&nbsp;Vercel.
                        </p>
                    </a>
                </div>
            </main>
        </>
    )
}

function AlgorithmBox({name, image}) {
    return (
        <div className={styles.algorithmBox}>
            <Image c
                   src={image}
                   alt="Image of algorithm"
                   className={styles.algorithmBoxImg}
            />
            <h2>{name}</h2>
        </div>
    )
}
