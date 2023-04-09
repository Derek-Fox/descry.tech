import styles from '@/styles/algorithm.module.css'

export default function Launch({file}) {
    return (
        <a href={file} download={file}>
            <button className={styles.launch}>Launch</button>
        </a>
    )
}