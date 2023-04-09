import styles from '@/styles/algorithm.module.css'
import Head from "next/head";
import Launch from "@/components/launch";

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Maze Solver</title>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" type='image/x-icon' href='../public/favicon.ico'/>
            </Head>

            <div className={styles.main}>
                <div className={styles.title}>
                    <h1>Maze Solver</h1>
                </div>
                <ul className={styles.description}>
                    <li>
                        <strong>Maze Solver</strong> is an algorithm for finding the <strong>path out of a maze</strong>.
                    </li>
                    <li>
                        Executing this algorithm performs a <strong>breadth-first-search</strong> with a <strong>traceback</strong>.
                    </li>
                </ul>

                <Launch file={"/maze-solver.jar"}></Launch>
            </div>

        </>
    );
}