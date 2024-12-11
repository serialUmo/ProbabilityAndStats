function plotx3Smooth()
    lowerBound = input('Enter lower bound: ');
    upperBound = input('Enter upper bound: ');
    points = input('How many points?: ');
    saltRange = input('Salt intensity?: ');
    window = input('Window size?: ');
    passes = input('Smoothing passes?: ')
    
    x = linspace(lowerBound, upperBound, points);
    y = x.^3;
    
    salt = saltRange * (rand(size(y)) - 0.5);
    ySalted = y + salt;
    
    ySmoothed = ySalted;
    for i = 1:passes
        ySmoothed = movmean(ySmoothed, window);
    end

    % Salted Function
    plot(x, ySalted, 'r', 'DisplayName', 'Salted Data');
    hold on;

    % Smooth Function
    plot(x, ySmoothed, 'g-', 'LineWidth', 2, 'DisplayName', 'Smoothed Data');

    % Original Function
    plot(x, y, 'b-', 'LineWidth', 2, 'DisplayName', 'Original Function');

    grid on;
    xlabel('x');
    ylabel('y');
    title('y = x^3');
    legend('show');
    hold off;
end
