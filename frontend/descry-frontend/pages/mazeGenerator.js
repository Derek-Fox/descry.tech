import styles from '@/styles/algorithm.module.css'
import Head from "next/head";
import Launch from "@/components/launch";

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Maze Generator</title>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" type='image/x-icon' href='../public/favicon.ico'/>
            </Head>

            <div className={styles.main}>
                <div className={styles.title}>
                    <h1>Maze Generator</h1>
                </div>
                <ul className={styles.description}>
                    <li>
                        The <strong>Maze Generator</strong> utilizes <strong>backtracking</strong> and <strong>random walks</strong> to define the maze.
                    </li>
                    <li>
                        When the generator reaches a <strong>dead-end</strong> in the maze, it will <strong>backtrack</strong> to start creating a new path.
                    </li>
                    <li>
                        After the generator has no where else to backtrack, <strong>the maze has been complete</strong>.
                    </li>
                </ul>

                <Launch file="/maze-generator.jar"></Launch>
            </div>

        </>
    );
}