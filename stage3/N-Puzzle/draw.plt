set term png size 1024,512
set xlabel "test"
set ylabel "searched nodes"

set xrange [0:50]
set xtics 0, 1, 50
set yrange [0:25000]
set ytics 0, 1000, 25000

set output "picture.png"
set grid
plot "result.txt" w lp lw 2 lc 2 ps 1 pt 5, "result02.txt" w lp lw 2 lc 1 ps 1 pt 6
set output
