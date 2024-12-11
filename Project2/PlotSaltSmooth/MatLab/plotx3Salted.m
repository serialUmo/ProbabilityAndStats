function plotx3Salted()
    lowerBound = input('Enter lower bound: ');
    upperBound = input('Enter upper bound: ');
    points = input('How many points?: ');
    saltRange = input('Salt intensity?: ');

    x = linspace(lowerBound, upperBound, points);
    y = x.^3;

    salt = saltRange * (rand(size(y)) - 0.5);
    ySalted = y + salt;
    
    % Salted Function
    plot(x, ySalted);
    hold on;

    % Original Function
    plot(x, y);

    grid on;
    xlabel('x');
    ylabel('y');
    title('y = x^3');
    legend('Salted', 'Original');
    hold off;
end
