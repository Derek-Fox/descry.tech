import styles from '@/styles/algorithm.module.css'
import Head from "next/head";
import Launch from "@/components/launch";

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Djikstra's Algorithm</title>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" type='image/x-icon' href='../public/favicon.ico'/>
            </Head>

            <div className={styles.main}>
                <div className={styles.title}>
                    <h1>Djikstra's Algorithm</h1>
                </div>
                <ul className={styles.description}>
                    <li>
                        <strong>Djikstra's Algorithm</strong> is an algorithm for finding the <strong>shortest weighted path</strong> between two nodes in a graph.
                    </li>
                    <li>
                        Executing this algorithm involves finding <strong>potential minimum distances</strong> between nodes until you find the <strong>optimal</strong> distance.
                    </li>
                    <li>
                        This algorithm also works similar to <strong>BFS</strong> in that it visits all nodes level-by-level.
                    </li>
                </ul>

                <Launch></Launch>
            </div>

        </>
    );
}