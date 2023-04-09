import styles from '@/styles/algorithm.module.css'

export default function Launch({file}) {
    return (
        <a href={file} download={file} target="_blank">
            <button className={styles.launch}>Launch</button>
        </a>
    )
}