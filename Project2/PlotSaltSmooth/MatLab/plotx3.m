function plotx3()
    lowerBound = input('Enter lower bound: ');
    upperBound = input('Enter upper bound: ');
    points = input('How many points?: ');

    x = linspace(lowerBound, upperBound, points);
    y = x.^3;

    plot(x, y);
    grid on;
    xlabel('x');
    ylabel('y');
    title('y = x^3');
end
